import React from 'react';
import { Button } from '@mui/material';

interface SubmitButtonProps {
    text: string; 
    variant?: "text" | "outlined" | "contained"; 
    color?: "primary" | "secondary" | "success" | "warning" | "error" | "info" | "default" | "complementary" | "ligth" | "dark";
    colorProperties?: "main" | "dark" | "light";
    disabled?: boolean; 
    onClick?: () => void; 
  }

const SubmitButton: React.FC<SubmitButtonProps> = ({
  text,
  variant = "contained",
  color = "primary",
  disabled = false,
  onClick,
  colorProperties = "main" ,
}) => {
  return (
    <Button
      type="submit"
      variant={variant}
      sx={{ backgroundColor: `${color}.${colorProperties}`, fontsize: '1.5rem', fontWeight: 'bold', padding: '10px 20px', borderRadius: '10px'}}
      disabled={disabled}
      onClick={onClick}>
      {text}
    </Button>
  );
};

export default SubmitButton;
