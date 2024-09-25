
import { Controller, SubmitHandler, useForm } from 'react-hook-form'
import { IHistoryFormInput } from './petInfo.component';
import { useState } from 'react';
import IHistoryResponse from '@/services/medicalHistory/interfaces/historyResponse.interface';
import useFetch from '@/hooks/fetch.hook';
import { MedicalHistoryService } from '@/services/medicalHistory/medicalHistory.service';
import BasicSelect from '@/components/UX/atoms/inputs/select.component';
import SubmitBtnComponent from '@/components/UX/atoms/buttons/submitBtn.component';

interface IProps {
  id: number;
}

const ClinicalHistory = ({ id }: IProps) => {

  const [historyId, setHistoryId] = useState('');
  const [loading, setLoading] = useState(false);

  let dateList: { id: string, name: string }[] = [];
  const clinicalHistory: IHistoryResponse[] = useFetch(() => MedicalHistoryService.getAllOrFilter(`patientId=${id}`), []);
  console.log(clinicalHistory);

  dateList = clinicalHistory.map((record) => {
    const date = new Date(record.appointment.date);
    const formmatedDate = date.toISOString().split('T')[0]
    return {
      id: record.id,
      name: formmatedDate
    }
  })


  const clinicalHistoryFile: Blob = useFetch(() => MedicalHistoryService.getFile(historyId), [historyId]);

  const generateFile: SubmitHandler<IHistoryFormInput> = (data) => {
    try {
      setLoading(true);
      setHistoryId(data.id);

    } catch (error) {
      console.error('Error al generar el PDF:', error);
    }
  }

  const seeFile: SubmitHandler<IHistoryFormInput> = () => {
    try {
      // Crear una URL desde el blob
      const fileUrl = window.URL.createObjectURL(new Blob([clinicalHistoryFile], { type: 'application/pdf' }));

      // Abrir el PDF en una nueva pestaña
      window.open(fileUrl, '_blank');

      return () => {
        window.URL.revokeObjectURL(fileUrl);
      };
    } catch (error) {
      console.error('Error al generar el PDF:', error);
    } finally {
      setLoading(false);
      // props.setCloseModal();
    }
  }

  const { control, handleSubmit,
    formState: { errors }
  } = useForm<IHistoryFormInput>();


  return (
    <form style={{ display: 'flex', width: '100%', gap: '1rem' }} onSubmit={loading ? handleSubmit(seeFile) : handleSubmit(generateFile)}>
      <Controller name='id' control={control} defaultValue="" rules={{
        required: {
          value: true,
          message: 'Campo requerido'
        }
      }} render={({ field }) => (
        <BasicSelect label={'Fecha'} items={dateList} field={field} error={errors.id} />
      )} />

      <SubmitBtnComponent text={loading ? 'Ver archivo' : 'Confirmar'} />
    </form>
  )
}

export default ClinicalHistory
