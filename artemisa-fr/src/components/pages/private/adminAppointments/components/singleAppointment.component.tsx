import { AppointmentsService } from "@/services/appointments/appointments.service";
import IAppointmentResponse from "@/services/appointments/interfaces/appointmentResponse.interface";
import styles from '../styles.module.css'
import { Typography } from "@mui/material";
import BasicSelect from "@/components/UX/atoms/inputs/select.component";
import { AppointmentState } from "@/models/enums/appointmentState.enum";
import { Controller, SubmitHandler, useForm, useWatch } from "react-hook-form";
import SubmitBtnComponent from "@/components/UX/atoms/buttons/submitBtn.component";
import { useEffect, useState } from "react";
import ReusableModal from "@/components/UX/molecules/modals/modal.component";
import SubmitButton from "@/components/UX/atoms/buttons/submitButtonLoginRegister.component";
import { useNavigate } from "react-router-dom";
import { IPatients } from "@/services/patients/patients.service";
import ModalComponent from "@/components/UX/atoms/modals/modal.components";
import UploadResultComponent from "./uploadResult.component";

interface IProps {
    appointment: IAppointmentResponse;
}

interface IFormInput {
    state: AppointmentState;
}

interface IState {
    appointment: IAppointmentResponse;
    patient: IPatients;
}

const SingleAppointmentComponent = ({ appointment }: IProps) => {

    const navigate = useNavigate()

    const [showStateBtn, setShowStateBtn] = useState(false);
    const [openConfirmationModal, setOpenConfirmationModal] = useState(false);
    const handleOpenConfirmationModal = () => setOpenConfirmationModal(true);
    const handleCloseConfirmationModal = () => setOpenConfirmationModal(false);
    const [modalText, setModalText] = useState('');

    const [openResultModal, setOpenResultModal] = useState(false);
    const handleOpenResultModal = () => setOpenResultModal(true);
    const handleCloseResultModal = () => setOpenResultModal(false);

    const { control, handleSubmit,
        formState: { errors }
    } = useForm<IFormInput>();

    const appointmentStates = Object.values(AppointmentState); //Get appointment states to show in select
    const statesWithId = appointmentStates.map(state => {
        return {
            id: state, name: state
        }
    })

    const selectedState = useWatch({
        control,
        name: 'state'
    })

    useEffect(() => {
        if (selectedState) {
            setShowStateBtn(selectedState !== appointment.state)
        }
    }, [selectedState])

    const date = new Date(appointment.date);
    const formattedDate = date.toISOString().split('T')[0]

    const stateSubmit: SubmitHandler<IFormInput> = async (data) => {
        try {
            const newState = await AppointmentsService.patch(appointment.id.toString(), { state: data.state });
            if (newState.statusCode === 200) {
                setModalText('Cambio de estado exitoso!');
            }

        } catch (error) {
            console.log(error);
            setModalText('Algo salió mal');
        } finally {
            handleOpenConfirmationModal()
        }
    }

    const handleHistoryClick = () => {

        const data: IState = {
            appointment: appointment,
            patient: appointment.patient
        }

        navigate('/history', { state: { data } })

    }

    const handleResultClick = () => {
        handleOpenResultModal();
    }
 
    return (
        <div className={styles.singleAppointmentContainer}>
            <h2>{appointment.service?.name}</h2>
            <div className={styles.gridContainer}>
                <Typography><span className={styles.infoTag}>Paciente: </span > {appointment.patient.name}</Typography>
                <Typography><span className={styles.infoTag}>Colaborador: </span > {appointment.collaborator?.name}</Typography>
                <Typography><span className={styles.infoTag}>Fecha: </span > {formattedDate}</Typography>
                <Typography><span className={styles.infoTag}>Hora: </span > {appointment.time}</Typography>


            </div>
            <form className={styles.gridContainer} onSubmit={handleSubmit(stateSubmit)}>
                <Controller name='state' control={control} defaultValue={appointment.state} render={({ field }) => (
                    <BasicSelect items={statesWithId} label={'Estado'} field={field} error={errors.state} />
                )}
                />
                {
                    showStateBtn && (<SubmitBtnComponent text={'Cambiar estado'} />)
                }
            </form>
            <ReusableModal open={openConfirmationModal} handleClose={handleCloseConfirmationModal} title={'Cambiar estado'} description={modalText} buttonText="Cerrar" />

            <div className={styles.gridContainer}>
                <SubmitButton text={'Crear historia clínica'} color={'complementary'} onClick={handleHistoryClick} />
                <SubmitButton text={'Subir resultados '} onClick={handleResultClick}/>
            </div>
            <ModalComponent open={openResultModal} onClose={handleCloseResultModal}>
                <UploadResultComponent appointment={appointment}/>
            </ModalComponent>
            

        </div>
    )
}

export default SingleAppointmentComponent;