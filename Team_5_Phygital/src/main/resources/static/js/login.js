function handleLogin(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    if (email === email && password === password) {
        window.location.href = 'dashboard';
    }
}

document.getElementById('loginForm').addEventListener('submit', handleLogin);