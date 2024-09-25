import { useState } from "react";
import { Controller, SubmitHandler, useForm } from "react-hook-form";
import { IHistoryFormInput } from "./petInfo.component";
import ITestResultResponse from "@/services/testResults/interfaces/testResultResponse.interface";
import useFetch from "@/hooks/fetch.hook";
import { TestResultsService } from "@/services/testResults/testResults.service";
import BasicSelect from "@/components/UX/atoms/inputs/select.component";
import SubmitBtnComponent from "@/components/UX/atoms/buttons/submitBtn.component";


interface IProps {
  id: number;
}

const TestResults = ({id}: IProps) => {


  const [loading, setLoading] = useState(false);
  const [testId, setTestId] = useState('');

  const generateTestFile: SubmitHandler<IHistoryFormInput> = (data) => {
    try {
      setLoading(true);
      setTestId(data.id);
    } catch (error) {
      console.log('Error al generar el PDF:', error)
    }
  }

  const seeTestFile:  SubmitHandler<IHistoryFormInput> = () => {
    try {

      const fileUrl = window.URL.createObjectURL(new Blob([testResultFile], { type: 'application/pdf' }));

      // Abrir el PDF en una nueva pestaña
      window.open(fileUrl, '_blank');

      return () => {
        window.URL.revokeObjectURL(fileUrl);
      };
    } catch (error) {
      console.log('Error al generar el PDF:', error)
    } finally {
      setLoading(false);
      // props.setCloseModal();
    }
  }

  const testResults: ITestResultResponse[] = useFetch(() => TestResultsService.getAllOrFilter(`patientId=${id}`), []);

  const testResultFile: Blob = useFetch(() => TestResultsService.getFile(testId), [testId]);

  const testsList = testResults.map(test => {
    const date = new Date(test.date);
    const formattedDate = date.toISOString().split('T')[0];
    return {
      id: test.id,
      name: `${test.service.name} - ${formattedDate}`
    }
  })

  const { control, handleSubmit,
    formState: { errors }
  } = useForm<IHistoryFormInput>();

  return (
    <form style={{ display: 'flex', width: '100%', gap: '1rem' }} onSubmit={loading ? handleSubmit(seeTestFile) : handleSubmit(generateTestFile)}>
          <Controller name='id' control={control} defaultValue="" rules={{
            required: {
              value: true,
              message: 'Campo requerido'
            }
          }} render={({ field }) => (
            <BasicSelect label={'Servicio'} items={testsList} field={field} error={errors.id} />
          )} />

          <SubmitBtnComponent text={loading ? 'Ver archivo' : 'Confirmar'} />
        </form>
  )
}

export default TestResults
