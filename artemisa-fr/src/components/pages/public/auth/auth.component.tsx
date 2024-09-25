import { useEffect, useState } from "react";
import LoginForm from "./login/login.component";
import RegisterForm from "./register/register.component";
import "./flippedp-login-register.styles.css";
import { Container } from "@mui/material";
import { useLocation } from "react-router-dom";

const Auth = ({ initialView }: { initialView: 'login' | 'register' }) => {
    const [isFlipped, setIsFlipped] = useState(initialView === 'register');
    const location = useLocation();

    useEffect(() => {
        if (location.pathname === '/login') {
            setIsFlipped(false);
        } else if (location.pathname === '/register') {
            setIsFlipped(true);
        }
    }, [location]);

    const handleFlip = () => {
        setIsFlipped(!isFlipped);
    };


    return (
        <Container maxWidth={false} sx={{
            backgroundImage: 'linear-gradient(rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.5)),url(static/assets/fondo_auth.jpg)',
            backgroundSize: 'cover',
            backgroundPosition: 'center',
            height: '140vh',
            width: '100vw',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
        }}>
            <div className={`auth-container ${isFlipped ? "flipped" : ""}`}>
                <div className="auth-card">
                    <div className="auth-card-front">
                        <LoginForm onSwitchToRegister={handleFlip} />
                    </div>
                    <div className="auth-card-back">
                        <RegisterForm onSwitchToLogin={handleFlip} />
                    </div>
                </div>
            </div>
        </Container >
    );
};

export default Auth;