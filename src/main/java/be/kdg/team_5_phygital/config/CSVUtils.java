package be.kdg.team_5_phygital.config;

import be.kdg.team_5_phygital.domain.Answers;
import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.domain.Session;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVUtils {
    public static void writeSessionToCSV(Session session, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            String[] header = { "id", "Name", "Note", "Question", "Answer" };
            writer.writeNext(header);

            List<Question> questions = session.getQuestions();
            List<Answers> answers = session.getAnswers();
            int size = Math.max(questions.size(), answers.size());

            for (int i = 0; i < size; i++) {
                String question = i < questions.size() ? questions.get(i).getText() : "";
                String answer = i < answers.size() ? answers.get(i).getAnswers() : "";

                String[] data = {
                        String.valueOf(session.getId()),
                        session.getUser().getName(),
                        session.getNote().getNote(),
                        question,
                        answer
                };
                writer.writeNext(data);
            }
        }
    }
}
