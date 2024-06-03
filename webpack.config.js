import MiniCssExtractPlugin from 'mini-css-extract-plugin'
import path from 'path'
import fs from 'fs'
import { fileURLToPath } from 'url'

// Utility function to recursively collect entry points
function getEntries(dir, entries = {}) {
    const files = fs.readdirSync(dir)

    files.forEach((file) => {
        const fullPath = path.join(dir, file)
        if (fs.statSync(fullPath).isDirectory()) {
            getEntries(fullPath, entries)
        } else if (file.match(/.*\.js$/)) {
            const entryName = path.relative('src/main/js', fullPath).replace(/\.js$/, '')
            entries[entryName] = `./${fullPath.replace(/\\/g, '/')}`
        }
    })

    return entries
}

const dirname = path.dirname(fileURLToPath(import.meta.url))
const entries = getEntries('./src/main/js')

const config = {
    entry: entries,
    output: {
        filename: (pathData) => {
            // Extracting the original path and adding 'bundle-' prefix to the filename
            const name = pathData.chunk.name
            const outputPath = path.dirname(name)
            const outputFilename = `bundle-${path.basename(name)}.js`
            return path.join(outputPath, outputFilename)
        },
        path: path.resolve(dirname, 'src/main/resources/static/js'),
        clean: true
    },
    devtool: 'source-map',
    mode: 'development',
    plugins: [
        new MiniCssExtractPlugin({
            filename: (pathData) => {
                // Ensuring CSS files follow the same directory structure
                const name = pathData.chunk.name
                const outputPath = path.dirname(name)
                const outputFilename = `bundle-${path.basename(name)}.css`
                return path.join('../css', outputPath, outputFilename)
            }
        })
    ],
    module: {
        rules: [
            {
                test: /\.s?css$/i,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    'sass-loader'
                ]
            },
            {
                test: /\.(png|svg|jpe?g|gif)$/i,
                type: 'asset'
            },
            {
                test: /\.(woff2?|eot|ttf|otf)$/i,
                type: 'asset'
            }
        ]
    },
    experiments: {
        topLevelAwait: true
    }
}

export default config
