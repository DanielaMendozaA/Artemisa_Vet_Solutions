import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { ControllerRenderProps } from 'react-hook-form';

interface IProps {
  field: ControllerRenderProps<any>;
  label: string;
  options?: 'disablePast' | 'disableFuture';
  error?: any;
}

const DatePickerComp = ({ field, label, options, error }: IProps) => {

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>

      <DatePicker
        disableFuture={options === 'disableFuture' ? true : false}
        disablePast={options === 'disablePast' ? true : false}
        views={['year', 'month', 'day']}
        label={label}
        value={field.value}
        onChange={(newValue) => newValue && field.onChange(newValue)}
        sx={{
          width: { xs: '100%', md: '80%' },
          borderRadius: '10px',
          
          '& .MuiInputLabel-root': {
            color: '#293241', // Color normal del label
          },
          '& .MuiInputLabel-root.Mui-focused': {
            color: '#293241', // Color del label cuando está enfocado
          },  
          '& .MuiOutlinedInput-root': {
            '&:hover fieldset': {
              borderColor: '#EE6C4D',
            },
            '&.Mui-focused fieldset': {
              borderColor: '#EE6C4D',
            },
          },
        }}
        slotProps={{
          textField: {
            fullWidth: true,
            variant: "outlined",
            error: !!error,
            helperText: error ? error.message : null,
          },
        }}
        
      />
    </LocalizationProvider>
  );
};

export default DatePickerComp