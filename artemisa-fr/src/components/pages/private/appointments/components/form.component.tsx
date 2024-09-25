import { ServicesService } from "../../../../../services/services/services.service";
import { useForm, SubmitHandler, Controller, useWatch } from "react-hook-form"
import styles from '../styles.module.css'
import BasicSelect from "../../../../UX/atoms/inputs/select.component";
import { useEffect, useState } from "react";
import { Dayjs } from "dayjs";
import { CollaboratorsService } from "../../../../../services/collaborator/collaborators.service";
import { IPatients, PatientsService } from "../../../../../services/patients/patients.service";
import useFetch from "../../../../../hooks/fetch.hook";
import SubmitBtnComponent from "../../../../UX/atoms/buttons/submitBtn.component";
import { AppointmentsService } from "../../../../../services/appointments/appointments.service";
import RowRadio from "../../../../UX/atoms/radio/radio.component";
import { ICreateAppointment } from "../../../../../services/appointments/interfaces/appointment.interfaces";
import { ConfirmationModal } from "@/components/UX/atoms/modals/confirmationModal.component";
import DatePickerComp from "@/components/UX/atoms/inputs/datePicker.component";

interface IFormInput {
    service: string;
    date: Dayjs;
    collaborator: string;
    patient: string;
    time: string;
}

interface IProps {
    tutorId: number;
}

const AppointmentForm = ({ tutorId }: IProps ) => {

    const [openModal, setOpenModal] = useState(false);
    const handleOpenModal = () => setOpenModal(true);
    const handleCloseModal = () => setOpenModal(false);
    const [modalText, setModalText] = useState('');

    const [query, setQuery] = useState('');
    const [availability, setAvailability] = useState([]);

    const services = useFetch(ServicesService.getAll);
    const { data: patients = [] }: { data: IPatients[] }  = useFetch(() => PatientsService.getAll(`tutorId=${tutorId}`));
    const collaborators = useFetch(() => CollaboratorsService.getAll(query), [query]);

    const { control, handleSubmit,
        formState: { errors }
    } = useForm<IFormInput>();

    const selectedService = useWatch({
        control,
        name: 'service'
    })

    const selectedCollaborator = useWatch({
        control,
        name: 'collaborator'
    })

    const selectedDate = useWatch({
        control,
        name: 'date'
    })

    useEffect(() => {
        async function fetchData() {
            try {
                if (selectedService && selectedDate && selectedCollaborator) {
                    const formattedDate = selectedDate.toISOString().split('T')[0]
                    const data = await AppointmentsService.getAvailability(`collaboratorId=${selectedCollaborator}&date=${formattedDate}`);
                    setAvailability(data.availableHours);
                }
            } catch (error) {
                console.log(error);
            }
        }
        fetchData()
    }, [selectedDate, selectedCollaborator, selectedService])



    useEffect(() => {
        if (selectedService) {
            setQuery(`serviceId=${selectedService}`);
        }
    }, [selectedService])

    const servicesNames: { id: string, name: string }[] = services.map((items: any) => {
        return { id: items.id, name: items.name }
    })

    const collaboratorsNames = collaborators.map((items: any) => {
        return { id: items.id, name: items.name }
    })

    const patientsNames = patients.map((items: any) => {
        return { id: items.id, name: items.name }
    })

    const renderAvailability = selectedCollaborator && selectedDate && selectedService

    const onSubmit: SubmitHandler<IFormInput> = async (data) => {
        try {

            const body: ICreateAppointment = {
                date: data.date.toISOString().split('T')[0],
                time: data.time,
                serviceId: parseInt(data.service),
                patientId: parseInt(data.patient),
                collaboratorId: parseInt(data.collaborator)
            }

            const response = await AppointmentsService.create(body);
            if(response.statusCode === 201) {
                setModalText('Cita agendada!');
                handleOpenModal();
            }
        } catch (error) {
            console.log(error)
            setModalText('Algo salió mal');
            handleOpenModal();
        }
    };

    return (

        <form className={styles.form} onSubmit={handleSubmit(onSubmit)} >
            <h1 className={styles.title}>Nueva Cita</h1>

            <Controller name='patient' control={control} defaultValue={""} rules={{
                required: {
                    value: true,
                    message: 'Campo requerido'
                },
            }} render={({ field }) => (
                <BasicSelect items={patientsNames} label={'Mascota'} field={field} error={errors.patient} />
            )}
            />

            <Controller name='service' control={control} defaultValue={""} rules={{
                required: {
                    value: true,
                    message: 'Campo requerido'
                },
            }} render={({ field }) => (
                <BasicSelect items={servicesNames} label={'Servicio'} field={field} error={errors.service} />
            )}
            />

            <Controller name='date' control={control} rules={{
                required: {
                    value: true,
                    message: 'Campo requerido'
                },
            }} render={({ field }) => (
                <DatePickerComp field={field} label='Fecha' options='disablePast' error={errors.date} />
            )}
            />

            <Controller name='collaborator' control={control} defaultValue={""} rules={{
                required: {
                    value: true,
                    message: 'Campo requerido'
                },
            }} render={({ field }) => (
                <BasicSelect items={collaboratorsNames} label={'Profesional'} field={field} error={errors.collaborator} />
            )}
            />

            {
                renderAvailability && (
                    <Controller name='time' control={control} rules={{
                        required: {
                            value: true,
                            message: 'Campo requerido'
                        },
                    }} render={({ field }) => (
                        <RowRadio items={availability} label='Horas disponibles' field={field} error={errors.time} />
                    )} />
                )
            }

            <SubmitBtnComponent text={'Agendar Cita'} />
            <ConfirmationModal text={modalText} open={openModal} onClose={handleCloseModal}/>
        </form>

    )
}
export default AppointmentForm;