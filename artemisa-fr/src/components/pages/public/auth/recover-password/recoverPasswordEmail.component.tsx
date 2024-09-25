import { useForm, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import PetsSharpIcon from '@mui/icons-material/PetsSharp';

import InputField from '../../../../UX/atoms/inputs/inputField.component';
import SubmitButton from '../../../../UX/atoms/buttons/submitButtonLoginRegister.component';
import { Box, Container, Paper, Typography } from '@mui/material';
import { usePasswordRecovery } from '../hooks/usePasswordRecovery';
import { useState } from 'react';
import ReusableModal from '@/components/UX/molecules/modals/modal.component';


const schema = yup.object().shape({
    email: yup.string().email('Correo electrónico inválido').required('Correo electrónico es requerido')
});

const RecoverPasswordEmail = () => {
    const [openModal, setOpenModal] = useState(false);
    const [openErrorModal, setOpenErrorModal] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const handleCloseModal = () => {
        setOpenModal(false);
    };
    const handleCloseErrorModal = () => setOpenErrorModal(false);

    const { control, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });

    const onSubmit = async (data: any) => {
        try {
            const response = await usePasswordRecovery({ ...data });
            console.log("respuesta desde el formulario de recover", response, typeof response);

            if (response === 201) {
                setOpenModal(true)
            } else if (response.includes('404')) {
                setErrorMessage('El correo electrónico no está registrado');
                setOpenErrorModal(true);
            } else {
                setErrorMessage('Error en el servidor');
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
                height: '80%',
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
                            gap: '10%',
                            width: '100%',
                            height: '100%'
                        }
                    }>
                    <Controller
                        name="email"
                        control={control}
                        defaultValue=""
                        render={({ field }) => (
                            <InputField
                                backgroundColor='primary.light'
                                label="Correo electrónico"
                                field={field}
                                error={errors.email}
                                endAdornment={<PetsSharpIcon />}
                            />
                        )}
                    />
                    <SubmitButton text="ENVIAR EMAIL" color='complementary' colorProperties='dark' />
                </form>
            </Paper>
            <ReusableModal
                open={openModal}
                handleClose={handleCloseModal}
                title="Cambio de contraseña"
                description="Por favor, revisa tu correo electrónico para cambiar tu contraseña."
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

export default RecoverPasswordEmail;