import React from 'react';
import { Modal, Typography, Paper, Link as MuiLink, Box } from '@mui/material';
import SubmitButton from '@/components/UX/atoms/buttons/submitButtonLoginRegister.component';

interface ReusableModalProps {
    open: boolean;
    handleClose: () => void;
    title: string;
    description: string;
    buttonText: string;
    linkText?: string;
    linkHref?: string;
    secondaryButtonText?: string;
    onSecondaryAction?: () => void;
}

const ReusableModal: React.FC<ReusableModalProps> = ({
    open,
    handleClose,
    title,
    description,
    buttonText,
    secondaryButtonText,
    onSecondaryAction,
    linkText,
    linkHref,
}) => {
    return (
        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="modal-title"
            aria-describedby="modal-description"
        >
            <Paper
                elevation={6}
                sx={{
                    position: 'absolute',
                    top: '50%',
                    left: '50%',
                    transform: 'translate(-50%, -50%)',
                    width: 400,
                    bgcolor: 'background.paper',
                    boxShadow: 24,
                    p: 4,
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    alignItems: 'center',
                    borderRadius: '25px',
                }}
            >
                <Typography id="modal-title" variant="h6" component="h2">
                    {title}
                </Typography>
                <Typography id="modal-description" sx={{ mt: 2 }}>
                    {description}
                </Typography>
                {linkText && linkHref && (
                    <MuiLink href={linkHref} sx={{ mt: 2 }}>
                        {linkText}
                    </MuiLink>
                )}
                <Box sx={{ mt: 3, display: 'flex', gap: 2 }}>
                    <SubmitButton text={buttonText} color="complementary" colorProperties="dark" onClick={handleClose} />

                    {secondaryButtonText && onSecondaryAction && (
                        <SubmitButton text={secondaryButtonText} color="primary" onClick={onSecondaryAction} colorProperties={'main'} />
                    )}
                </Box>
            </Paper>
        </Modal>
    );
};

export default ReusableModal;
