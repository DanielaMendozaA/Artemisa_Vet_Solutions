import { useForm, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import { useNavigate } from 'react-router-dom';

import InputField from '../../../../UX/atoms/inputs/inputField.component';
import SubmitButton from '../../../../UX/atoms/buttons/submitButtonLoginRegister.component';
import { Box, Container, IconButton, Paper, Typography } from '@mui/material';
import { useState } from 'react';
import ReusableModal from '@/components/UX/molecules/modals/modal.component';
import { useSearchParams } from 'react-router-dom';
import { usePasswordNewPass } from '../hooks/usePasswordSendNewPass';

interface FormData {
    newPassword: string;
    confirmPassword: string;
}

const schema = yup.object().shape({
    newPassword: yup.string()
        .required('Contraseña es requerida')
        .min(6, 'La contraseña debe tener al menos 6 caracteres')
        .max(50, 'La contraseña debe tener máximo 50 caracteres')
        .matches(
            /(?:(?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/,
            'La contraseña debe tener una letra mayúscula, una letra minúscula y un número'
        ),
    confirmPassword: yup.string()
        .oneOf([yup.ref('newPassword')], 'Las contraseñas deben coincidir')
        .required('Confirmar contraseña es requerida')
});


const RecoverPasswordNewPassword = () => {
    const [showPassword, setShowPassword] = useState(false);
    const [searchParams] = useSearchParams();
    const token = searchParams.get('token');
    const [openModal, setOpenModal] = useState(false);
    const [openErrorModal, setOpenErrorModal] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const navigate = useNavigate();

    const handleRedirectToLogin = () => {
        navigate('/login');
    };


    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    };

    const handleCloseModal = () => {
        setOpenModal(false);
    };
    const handleCloseErrorModal = () => setOpenErrorModal(false);

    const { control, handleSubmit, formState: { errors } } = useForm<FormData>({
        resolver: yupResolver(schema)
    });

    const onSubmit = async (data: FormData) => {
        try {
            if (token) {
                const response = await usePasswordNewPass({ password: data.newPassword, token });
                console.log("respuesta desde el formulario component", response);
                if (response === 200) {
                    setOpenModal(true);
                } else if (response.includes('400')) {
                    setErrorMessage('Token inválido');
                    setOpenErrorModal(true);
                } else {
                    setErrorMessage('Error en el servidor');
                    setOpenErrorModal(true);
                }
            } else {
                setErrorMessage('Token no encontrado');
                setOpenErrorModal(true);
            }

        } catch (error: any) {
            console.error("Error en la petición desde coponent", error.message);
        }
    };

    return (
        <Container maxWidth={false} sx={{
            backgroundImage: 'linear-gradient(rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.5)),url(static/assets/fondo_auth.jpg)',
            backgroundSize: 'cover',
            backgroundPosition: 'center',
            height: '100vh',
            width: '100vw',
            display: 'flex',
            justifyContent: 'center'
        }}>
            <Paper elevation={10} sx={{
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
                alignItems: 'center',
                marginTop: '2%',
                width: '70%',
                height: '90%',
                borderRadius: '10px',
                padding: '5%',
                backgroundColor: "rgba(255, 255, 255, 0.8)",
            }}>
                <Typography variant="h1" align="center" gutterBottom sx={{ color: '#10353C' }}>
                    Recuperar Contraseña
                </Typography>
                <Box sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                }}>
                    <img src="static/assets/logo_artemisa_2.png" alt="Perro" style={{ width: '25vw', height: '30vh' }} />
                </Box>
                <form onSubmit={handleSubmit(onSubmit)}
                    style={
                        {
                            display: 'flex',
                            flexDirection: 'column',
                            justifyContent: 'center',
                            alignItems: 'center',
                            gap: '3vh',
                            width: '100%',
                            height: '100%'
                        }
                    }>
                    <Controller
                        name="newPassword"
                        control={control}
                        defaultValue=""
                        render={({ field }) => (
                            <InputField
                                backgroundColor='primary.light'
                                label="Contraseña"
                                type={showPassword ? 'text' : 'password'}
                                field={field}
                                error={errors.newPassword}
                                endAdornment={(
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={handleClickShowPassword}
                                        edge="end"
                                    >
                                        {showPassword ? <VisibilityOff /> : <Visibility />}
                                    </IconButton>
                                )}
                            />
                        )}
                    />
                    <Controller
                        name="confirmPassword"
                        control={control}
                        defaultValue=""
                        render={({ field }) => (
                            <InputField
                                backgroundColor='primary.light'
                                    label="Contraseña"
                                type={showPassword ? 'text' : 'password'}
                                field={field}
                                error={errors.confirmPassword}
                                endAdornment={(
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={handleClickShowPassword}
                                        edge="end"
                                    >
                                        {showPassword ? <VisibilityOff /> : <Visibility />}
                                    </IconButton>
                                )}
                            />
                        )}
                    />
                    <SubmitButton text="CONFIRMAR" color='complementary' colorProperties='dark' />
                </form>
            </Paper>
            <ReusableModal
                open={openModal}
                handleClose={handleCloseModal}
                title="Cambio de contraseña"
                description="Tu contraseña ha sido cambiada exitosamente"
                secondaryButtonText="Ir al Login"
                onSecondaryAction={handleRedirectToLogin}
                buttonText="CERRAR"
            />
            <ReusableModal
                open={openErrorModal}
                handleClose={handleCloseErrorModal}
                title="Error"
                description={errorMessage}
                buttonText="CERRAR"
            />
        </Container >
    );
};

export default RecoverPasswordNewPassword;