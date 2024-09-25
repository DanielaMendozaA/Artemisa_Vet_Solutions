import DatePickerComp from "@/components/UX/atoms/inputs/datePicker.component";
import InputField from "@/components/UX/atoms/inputs/inputField.component";
import BasicSelect from "@/components/UX/atoms/inputs/select.component";
import useFetch from "@/hooks/fetch.hook";
import { AppointmentState } from "@/models/enums/appointmentState.enum";
import { CollaboratorsService } from "@/services/collaborator/collaborators.service";
import ICollaboratorResponse from "@/services/collaborator/interfaces/collaboratorResponse.interface";
import { IService, ServicesService } from "@/services/services/services.service";
import { Typography } from "@mui/material"
import { Controller, SubmitHandler, useForm } from "react-hook-form";
import styles from './styles.module.css'
import TableComponent from "@/components/UX/molecules/tables/table.component";
import { useState } from "react";
import IAppointmentResponse from "@/services/appointments/interfaces/appointmentResponse.interface";
import { AppointmentsService } from "@/services/appointments/appointments.service";
import SubmitButton from "@/components/UX/atoms/buttons/submitButtonLoginRegister.component";
import ModalComponent from "@/components/UX/atoms/modals/modal.components";
import SingleAppointmentComponent from "./components/singleAppointment.component";
import MedicalHistoryForm from "./components/medicalHistoryForm.component";


interface IFormInput {
    service?: string;
    date?: string;
    tutor?: string;
    state?: string;
    collaborator?: string;
}

const AdminAppointments = () => {

    const [selectedRowId, setSelectedRowId] = useState<string>('');
    const [openModal, setOpenModal] = useState(false);
    const [appointment, setAppointment] = useState<IAppointmentResponse | null>(null)

    const handleOpenModal = () => setOpenModal(true);
    const handleCloseModal = () => setOpenModal(false);

    const handleRowClick = async (rowId: string) => {
        const appointment = await AppointmentsService.getById(rowId);
        setAppointment(appointment);
        setSelectedRowId(rowId);
        handleOpenModal();
    };

    const [query, setQuery] = useState('');

    const { control, handleSubmit, reset,
        formState: { errors }
    } = useForm<IFormInput>();

    const services: IService[] = useFetch(ServicesService.getAll); //Get all services to show in select
    const collaborators: ICollaboratorResponse[] = useFetch(CollaboratorsService.getAll); //Get all colabs
    const appointments: IAppointmentResponse[] = useFetch(() => AppointmentsService.getAllOrFilter(query && query), [query]); //Get all appointments or filter

    const servicesNames: { id: string, name: string }[] = services.map((items) => {//Format of object to use in select
        return { id: items.id, name: items.name }
    })

    const collaboratorsWithId = collaborators.map((collab) => {
        return { id: collab.id, name: collab.name }
    })

    const appointmentStates = Object.values(AppointmentState); //Get appointment states to show in select
    const statesWithId = appointmentStates.map(state => {
        return {
            id: state, name: state
        }
    })

    const columns = [{ key: 'patientName', label: 'Nombre del paciente' }, { key: 'service', label: 'Servicio' }, { key: 'date', label: 'Fecha' }, { key: 'time', label: 'Hora' }, { key: 'collaborator', label: 'Collaborador' }, { key: 'state', label: 'Estado' }];

    const rows = appointments.map(appointment => {
        const date = new Date(appointment.date);
        const formattedDate = date.toISOString().split('T')[0];
        return {
            id: appointment.id,
            patientName: appointment.patient.name,
            service: appointment.service?.name,
            date: formattedDate,
            time: appointment.time,
            collaborator: appointment.collaborator?.name,
            state: appointment.state
        }
    })

    const onSubmit: SubmitHandler<IFormInput> = (data) => {
        try {
            let queryList: string[] = [];
            if (data.service) queryList.push(`serviceId=${data.service}`);

            if (data.state) {
                if (data.state === 'En progreso') data.state = 'En%20progreso'
                queryList.push(`state=${data.state}`)
            };

            if (data.date) {
                const date = new Date(data.date);
                const formattedDate = date.toISOString().split('T')[0]
                queryList.push(`date=${formattedDate}`);
            }

            if (data.collaborator) queryList.push(`collaboratorId=${data.collaborator}`);

            if (data.tutor) queryList.push(`tutorIdentification=${data.tutor}`)
            setQuery(queryList.join('&'));

        } catch (error) {
            console.log(error);
        }
    }

    const handleReset = () => {
        // Restablece los campos del formulario a sus valores predeterminados
        reset({
            service: "",
            date: undefined,
            tutor: "",
            state: "",
            collaborator: "",
        });

        setQuery('');

    };


    return (
        <div style={{ height: '90vh' }}>
            <Typography variant={'h2'} sx={{ margin: '2rem' }}>Citas</Typography>
            <form className={styles.filterForm} onSubmit={handleSubmit(onSubmit)}>
                <Controller name='service' control={control} defaultValue="" render={({ field }) => (
                    <BasicSelect items={servicesNames} label={'Servicio'} field={field} error={errors.service} />
                )}
                />
                <Controller name='date' control={control} render={({ field }) => (
                    <DatePickerComp field={field} label='Fecha' error={errors.date} />
                )}
                />
                <Controller name='tutor' control={control} defaultValue="" rules={{
                    minLength: {
                        value: 3,
                        message: 'Debe contener más de 3 caracteres'
                    }
                }} render={({ field }) => (
                    <InputField label={'N° de identificación del tutor'} field={field} error={errors.tutor} />
                )} />
                <Controller name='state' control={control} defaultValue={""} render={({ field }) => (
                    <BasicSelect items={statesWithId} label={'Estado'} field={field} error={errors.state} />
                )}
                />
                <Controller name='collaborator' control={control} defaultValue={""} render={({ field }) => (
                    <BasicSelect items={collaboratorsWithId} label={'Colaborador'} field={field} error={errors.collaborator} />
                )}
                />
                <SubmitButton text={'Aplicar'} color={'complementary'} colorProperties='main' />
                <SubmitButton text={'Borrar filtros'} onClick={handleReset} />

            </form>
            <div className={styles.tableContainer}>
                <TableComponent columns={columns} rows={rows} onRowClick={handleRowClick} />
            </div>
            {appointment &&
                <ModalComponent open={openModal} onClose={handleCloseModal}>
                    <SingleAppointmentComponent appointment={appointment}></SingleAppointmentComponent>
                </ModalComponent>
            }


        </div>

        // <MedicalHistoryForm/>

    )
}

export default AdminAppointments