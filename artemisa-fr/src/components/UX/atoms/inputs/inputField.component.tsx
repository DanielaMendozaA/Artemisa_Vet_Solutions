import React from 'react';
import { InputAdornment, TextField } from '@mui/material';
import { ControllerRenderProps, FieldError } from 'react-hook-form';

interface InputFieldProps {
  label: string;
  field: ControllerRenderProps<any, any>;
  error?: FieldError;
  type?: string;
  icon?: React.ReactNode;
  autoComplete?: string;
  endAdornment?: React.ReactNode;
  backgroundColor?: 'primary.light'

}

const InputField: React.FC<InputFieldProps> = ({ backgroundColor ,label, field, error, autoComplete = "off",  type = 'text', endAdornment }) => {
  return (
    <TextField
      {...field}
      label={label}
      type={type}
      variant="outlined"
      error={!!error}
      helperText={error ? error.message : null}
      autoComplete={autoComplete}
      slotProps={{
        input: {
          endAdornment: endAdornment ? (
            <InputAdornment position="end">
              {endAdornment}
            </InputAdornment>
          ) : null,
        },
      }}
      sx={{
        width: { xs: '100%', md: '80%' },  
        borderRadius: '10px',
        backgroundColor: backgroundColor ? backgroundColor : 'ligth',
        '& fieldset': { 
          borderColor: 'complementary.main',
          borderRadius: '10px',
        },
        '&:hover fieldset': {
          borderColor: 'complementary.main',
        }
      }}
    />
  );
};

export default InputField;