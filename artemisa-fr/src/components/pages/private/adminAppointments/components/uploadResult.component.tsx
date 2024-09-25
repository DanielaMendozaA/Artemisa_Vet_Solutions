import { Typography } from "@mui/material"
import styles from '../styles.module.css';
import { useState } from "react";
import SubmitBtnComponent from "@/components/UX/atoms/buttons/submitBtn.component";
import { Controller, SubmitHandler, useForm } from "react-hook-form";
import FileInputField from "@/components/UX/atoms/inputs/fileUpload.component";
import IAppointmentResponse from "@/services/appointments/interfaces/appointmentResponse.interface";
import ICreateTestResult from "@/services/testResults/interfaces/createTestResult.interface";
import { TestResultsService } from "@/services/testResults/testResults.service";
import { useNavigate } from "react-router-dom";
import ReusableModal from "@/components/UX/molecules/modals/modal.component";

interface IFormInput {
  file: File;
}

interface IProps {
  appointment: IAppointmentResponse
}

const UploadResultComponent = ({ appointment }: IProps) => {

  const navigate = useNavigate()

  const { control, handleSubmit } = useForm<IFormInput>();

  const [openModal, setOpenModal] = useState(false);
  const handleOpenModal = () => setOpenModal(true);
  const handleCloseModal = () => {
    setOpenModal(false)
    navigate('/admin/appointments')
  };
  const [modalText, setModalText] = useState('');

  const onSubmit: SubmitHandler<IFormInput> = async(data) => {
    try {

      const date= new Date(appointment.date);
      const formattedDate = date.toISOString().split('T')[0];
      const body: ICreateTestResult = {
        date: formattedDate,
        patientId: appointment.patient.id,
        serviceId: appointment.service?.id || 0,
        file: data.file
      }

      const newFile = await TestResultsService.uploadFile(body);
      if(newFile.statusCode === 201) {
        setModalText('Archivo subido con éxito!')
      }
    } catch (error) {
      console.log(error);
      setModalText('Algo salió mal')
    } finally {
      handleOpenModal();
    }
  };

  return (
    <div className={styles.singleAppointmentContainer}>
      <Typography variant={'h3'}>Subir resultados de exámenes</Typography>
      <form onSubmit={handleSubmit(onSubmit)} className={styles.flexColumn}>

        <Controller
          name="file"
          control={control}
          render={({ field }) => (
            <FileInputField field={field} />
          )}
        />

        <SubmitBtnComponent text="Confirmar" />
      </form>
      <ReusableModal open={openModal} handleClose={handleCloseModal} title={'Resultados de exámenes'} description={modalText} buttonText="Cerrar" />

    </div>
  );
};

export default UploadResultComponent;
