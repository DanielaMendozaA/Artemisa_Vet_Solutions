import InputField from "@/components/UX/atoms/inputs/inputField.component";
import { Controller, SubmitHandler, useForm } from "react-hook-form";
import styles from '../styles.module.css'
import { Typography } from "@mui/material";
import BasicSelect from "@/components/UX/atoms/inputs/select.component";
import SubmitButton from "@/components/UX/atoms/buttons/submitButtonLoginRegister.component";
import ICreateHistory from "@/services/medicalHistory/interfaces/createHistory.interface";
import { useLocation, useNavigate } from "react-router-dom";
import { MedicalHistoryService } from "@/services/medicalHistory/medicalHistory.service";
import { useState } from "react";
import ReusableModal from "@/components/UX/molecules/modals/modal.component";

interface IFormInput {
  previousIllnesses: string,
  consultationReason: string,
  respiratoryRate: string,
  heartRate: string,
  pulse: string,
  crt: string;
  temperature: string;
  limphaticNodes: string;
  mucosa: string;
  temperament: string;
  findings: string;
  tests: string;
  weight: string;
  alimentation: string;
  sterilized: string;
}

const MedicalHistoryForm = () => {

  const { state } = useLocation();
  const navigate = useNavigate();

  const [openModal, setOpenModal] = useState(false);
  const handleOpenModal = () => setOpenModal(true);
  const handleCloseModal = () => {
    setOpenModal(false)
    navigate('/admin/appointments')
  };
  const [modalText, setModalText] = useState('');

  const { control, handleSubmit,

  } = useForm<IFormInput>();

  const sterilizedField = [{
    id: 'yes',
    name: 'Si'
  }, {
    id: 'no',
    name: 'No'
  }]

  const onSubmit: SubmitHandler<IFormInput> = async (data) => {
    try {
      const body: ICreateHistory = {
        content: {
          previousIllnesses: data.previousIllnesses,
          consultationReason: data.consultationReason,
          respiratoryRate: parseFloat(data.respiratoryRate),
          heartRate: parseFloat(data.heartRate),
          pulse: data.pulse,
          CRT: parseFloat(data.crt),
          temperament: data.temperament,
          temperature: parseFloat(data.temperature),
          mucosa: data.mucosa,
          limphaticNodes: data.limphaticNodes,
          findings: data.findings,
          tests: data.tests
        },
        patientState: {
          weight: parseFloat(data.weight),
          alimentation: data.alimentation,
          sterilized: data.sterilized === 'yes' ? true : false
        },
        patientId: state.data.patient.id,
        appointmentId: state.data.appointment.id
      }

      const newClinicalHistory = await MedicalHistoryService.create(body);

      if (newClinicalHistory.statusCode === 201) {
        setModalText('Historia clínica guardada con éxito!');
      }

    } catch (error) {
      console.log(error);
      setModalText('Algo salió mal');
    } finally {
      handleOpenModal()
    }
  }

  return (
    <div style={{ height: '90vh' }}>
      <Typography sx={{ margin: '2rem 2rem 0 2rem ' }} variant={'h1'}>Historia clínica de {state.data.patient.name}</Typography>
      <form className={styles.flexColumn} onSubmit={handleSubmit(onSubmit)}>
        <div className={`${styles.historyFormGrid}`}>
          <Controller
            name="weight"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Peso (kg)"} type={'number'} field={field} />
            )}
          />

          <Controller
            name="sterilized"
            control={control}
            defaultValue=''
            render={({ field }) => (
              <BasicSelect
                label={"Esterilizado"}
                items={sterilizedField}
                field={field}
              // error={errors.gender}
              />
            )}
          />

          <Controller
            name="alimentation"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Alimentación"} field={field} />
            )}
          />
          <Controller
            name="previousIllnesses"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Enfermedades previas"} field={field} />
            )}
          />
          <Controller
            name="consultationReason"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Motivo de consulta"} field={field} />
            )}
          />

          <Controller
            name="heartRate"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Frecuencia cardiaca (lpm)"} type={'number'} field={field} />
            )}
          />

          <Controller
            name="respiratoryRate"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Frecuencia respiratoria (rpm)"} type={'number'} field={field} />
            )}
          />

          <Controller
            name="pulse"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Características del pulso"} field={field} />
            )}
          />

          <Controller
            name="crt"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Tiempo de llenado capilar (sg)"} type={'number'} field={field} />
            )}
          />

          <Controller
            name="temperature"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Temperatura (°c)"} type={'number'} field={field} />
            )}
          />

          <Controller
            name="temperament"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Temperamento"} field={field} />
            )}
          />
          <Controller
            name="mucosa"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Descripción de las mucosas"} field={field} />
            )}
          />

          <Controller
            name="limphaticNodes"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Descripción de nodos linfáticos"} field={field} />
            )}
          />

          <Controller
            name="findings"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Hallazgos clínicos"} field={field} />
            )}
          />

          <Controller
            name="tests"
            control={control}
            defaultValue={''}
            render={({ field }) => (
              <InputField label={"Exámenes complementarios"} field={field} />
            )}
          />
        </div>
        <div style={{ display: 'flex', justifyContent: 'center', paddingBottom: '2rem' }}>
          <SubmitButton text={'Guardar'} />
        </div>

      </form>
      <ReusableModal open={openModal} handleClose={handleCloseModal} title={'Historia clínica'} description={modalText} buttonText="Cerrar" />
    </div>
  )
}

export default MedicalHistoryForm