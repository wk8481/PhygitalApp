document.addEventListener('DOMContentLoaded', () => {
    const createBubble = () => {
        const bubble = document.createElement('div');
        bubble.classList.add('bubble');
        bubble.style.left = `${Math.random() * 100}vw`;
        bubble.style.animationDuration = `${Math.random() * 2 + 3}s`;
        bubble.style.setProperty('--i', `${Math.random() * 10}`);
        document.body.appendChild(bubble);
        setTimeout(() => {
            bubble.remove();
        }, 5000);
    };
    setInterval(createBubble, 300);
});
