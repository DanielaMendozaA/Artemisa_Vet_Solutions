import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { ControllerRenderProps, FieldError } from 'react-hook-form';
import { FormHelperText } from '@mui/material';

interface IProps {
  items: { id: string | number , name: string }[];
  label: string;
  field: ControllerRenderProps<any>;
  error?: FieldError;
}

const BasicSelect = ({ items, label, field, error }: IProps) => {
  return (
    <Box sx={{
      minWidth: 120, width: { xs: '100%', md: '80%' }, '& .MuiOutlinedInput-root': {
        '&:hover fieldset': {
          borderColor: '#EE6C4D',
        },
        '&.Mui-focused fieldset': {
          borderColor: '#EE6C4D',
        },
      },
    }}>
      <FormControl fullWidth>
        <InputLabel id="basic-select-label" sx={{
          
          color: '#293241', '&.Mui-focused': {
            color: '#293241', // Color cuando está enfocado
          }
        }}>{label}</InputLabel>
        <Select
          labelId="basic-select-label"
          id="basic-select"
          value={field.value}
          label={label}
          error={!!error}
          onChange={field.onChange}
          sx={{borderRadius: '10px'}}
        >
          {items.map((item) => (
            <MenuItem key={item.id} value={item.id}>
              {item.name}
            </MenuItem>
          ))}
        </Select>
        <FormHelperText>{error ? error.message : null}</FormHelperText>
      </FormControl>
    </Box>
  );
};


export default BasicSelect;
