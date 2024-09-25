import { Modal } from "@mui/material";

interface IProps {
    open: boolean;
    onClose: () => void;
    children: JSX.Element;
}

const ModalComponent = ({ open, onClose, children }: IProps) => {
    return (
        <Modal
            open={open}
            onClose={onClose}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
            sx={{
                width: '100%',
                height: '100%',
                overflow: 'scroll',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center'

            }}
        >
            { children }

        </Modal>
    )
}

export default ModalComponent