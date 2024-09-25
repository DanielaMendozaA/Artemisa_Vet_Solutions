import { Modal, Box, Typography } from "@mui/material";

interface IProps {
    text: string;
    open: boolean;
    onClose: () => void;
}
export function ConfirmationModal({ text, open, onClose }: IProps) {


    return (
            
            <Modal
                open={open}
                onClose={onClose}
                aria-labelledby="child-modal-title"
                aria-describedby="child-modal-description"
                sx={{
                    width: '100%',
                    height: '100%',
                    overflow: 'scroll',
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center'
    
                }}
            >
                <Box sx={{backgroundColor: 'white', width:'40%', height:'30%', display: 'flex', justifyContent: 'center', alignItems:'center', borderRadius: '8px'}}>
                    <Typography variant='h2' id="child-modal-title">{text}</Typography>
                </Box>
            </Modal>
    );
}