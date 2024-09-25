import { Typography } from '@mui/material';
import styles from '@/components/pages/private/appointments/styles.module.css';
import SubmitBtnComponent from '@/components/UX/atoms/buttons/submitBtn.component';
import { useState } from 'react';
// import useFetch from '@/hooks/fetch.hook';
// import { MedicalHistoryService } from '@/services/medicalHistory/medicalHistory.service';
// import BasicSelect from '@/components/UX/atoms/inputs/select.component';
// import { Controller, SubmitHandler, useForm } from 'react-hook-form';
// import ITestResultResponse from '@/services/testResults/interfaces/testResultResponse.interface';
// import { TestResultsService } from '@/services/testResults/testResults.service';
import { Pen } from 'lucide-react';
import PetFormComponent from '@/components/pages/private/appointments/components/petForm.component';
import ClinicalHistory from './Clinical-history.component';
import TestResults from './Test-results.component';
import ModalComponent from '@/components/UX/atoms/modals/modal.components';

interface IProps {
  id: number;
  name: string;
  breed?: string;
  specie: string;
  dob?: string;
  gender: string;
  weight?: number;
  color?: string;
  setCloseModal: () => void;
  tutorId?: number;
  idNumber?: string;
  setData?: (val: boolean) => void;
}

export interface IHistoryFormInput {
  id: string;
}

const PetInfoComponent = (props: IProps) => {
  const [hover, setHover] = useState(false);
  const [getClinicalHistory, setGetClinicalHistory] = useState(false)
  const [getTestResult, setGetTestResult] = useState(false);


  const [openPetModal, setOpenPetModal] = useState(false);

  const handleOpenPetModal = () => setOpenPetModal(true);
  const handleClosePetModal = () => setOpenPetModal(false);

  const handleClinicalHistory = () => {
    setGetClinicalHistory(true);
    setGetTestResult(false);
  }

  const handleTestResult = () => {
    setGetTestResult(true);
    setGetClinicalHistory(false);
  }

  


  return (
    <div className={styles.petInfoContainer} style={{position: 'relative'}}>
      <Pen size={30} style={{
        position: 'absolute',
        top: '2rem',
        right: '2rem',
        cursor: 'pointer',
        transition: '3 ease',
        color: hover ? 'red' : 'black'
      }} onClick={handleOpenPetModal}
      onMouseEnter={() => setHover(true)}
      onMouseLeave={() => setHover(false)} />
      <Typography variant='h3'>{props.name}</Typography>
      <div className={styles.petInfo}>
        { props.tutorId && <Typography>
          <span className={styles.infoLabel}>Tutor:</span> {props.tutorId}
        </Typography>
          }
          { props.idNumber && <Typography>
          <span className={styles.infoLabel}>Nº de identificación:</span> {props.idNumber}
        </Typography>
          }
        <Typography>
          <span className={styles.infoLabel}>Especie:</span> {props.specie}
        </Typography>
        <Typography>
          <span className={styles.infoLabel}>Raza:</span> {props.breed || 'No registra'}
        </Typography>
        <Typography>
          <span className={styles.infoLabel}>Color:</span> {props.color || 'No registra'}
        </Typography>
        <Typography>
          <span className={styles.infoLabel}>Fecha de nacimiento:</span> {props.dob || 'No registra'}
        </Typography>
        <Typography>
          <span className={styles.infoLabel}>Sexo:</span> {props.gender}
        </Typography>
        <Typography>
          <span className={styles.infoLabel}>Peso:</span> {props.weight || 'No registra'}
        </Typography>
      </div>
      <div className={styles.btnContainer}>
        <SubmitBtnComponent text={'Ver historia clínica'} onClick={handleClinicalHistory} />
        <SubmitBtnComponent text={'Ver resultados de exámenes'} onClick={handleTestResult} />
      </div>
      {
        getClinicalHistory &&
        <ClinicalHistory  id={props.id}/>
        // handleSubmit={handleSubmit} errors={errors} seeFile={seeFile} generateFile={generateFile} dateList={dateList} loading={loading} 
      }
      {
        getTestResult &&
        <TestResults id={props.id}/>
      }
      <ModalComponent open={openPetModal} onClose={handleClosePetModal}>
          <PetFormComponent tutorId={1234} setData={props.setData} isEdit name={props.name} specie={props.specie} breed={props.breed} petGender={props.gender}  color={props.color} id={props.id}/>
      </ModalComponent>
    </div>
  )
}

export default PetInfoComponent