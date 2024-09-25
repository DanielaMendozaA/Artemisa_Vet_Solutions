import { Box, Typography } from '@mui/material';
import React from 'react';
import styles from './styles.module.css'

interface InputFieldProps {
  field: any;
  handleChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
  error?: any;
  label?: string;
  endAdornment?: React.ReactNode;
}

const FileInputField: React.FC<InputFieldProps> = ({ field, handleChange, error, label }) => {
  return (
    <Box sx={{ width: { xs: '100%', md: '80%' }, margin: '1rem', display: 'flex', justifyContent: 'center', borderRadius: '10px' }}>
      {label && (
        <Typography variant="subtitle1" sx={{ marginBottom: '8px', color: 'ligth.main' }}>
          {label}
        </Typography>
      )}
      <Box
        component="label"
        sx={{
          padding: '0.6rem',
          borderRadius: '10px',
          backgroundColor: 'primary.main',
          borderColor: error ? 'error.main' : 'complementary.main',

        }}
      >
        <input
          type="file"
          accept="application/pdf"
          style={{fontFamily:'Dosis'}}
          className={styles.inputFile}
          onChange={(e) => {
            // handleChange(e);
            field.onChange(e.target.files?.[0]);
          }}

        />

      </Box>
      {error && (
        <Typography variant="caption" color="error.main" sx={{ marginTop: '4px' }}>
          {error.message}
        </Typography>
      )}
    </Box>
  );
};

export default FileInputField;
