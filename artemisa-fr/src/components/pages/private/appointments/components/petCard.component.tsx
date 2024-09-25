import { useEffect, useState } from 'react';
import styles from '../styles.module.css'
import ModalComponent from '../../../../UX/atoms/modals/modal.components';
import PetInfoComponent from '../../../../UX/molecules/modals/Patients/petInfo.component';


interface PetCardProps {

    id: number;
    name: string;
    breed?: string;
    specie: string;
    dob?: string;
    gender: string;
    weight?: number;
    color?: string;
    tutorId?: string;
    setData?: (val: boolean) => void;

}

function PetCardComponent(props: PetCardProps) {

    const [openModal, setOpenModal] = useState(false);

    const handleOpenModal = () => setOpenModal(true);
    const handleCloseModal = () => setOpenModal(false);
    console.log(props.id);
    

    
    return (
        <>
        <div className={styles.petCard} onClick={handleOpenModal} >
            <div className={styles.content}>
                <div className={styles.header}>
                    <div className={styles.imageContainer}>
                        <img
                            src='static/assets/paw-print.png'
                            alt={`${props.name}'s paw print`}
                            className={styles.image}
                        />
                    </div>
                    <h2 className={styles.name}>{props.name}</h2>
                </div>
                <div className={styles.infoContainer}>
                    <p className={styles.infoText}>
                        <span className={styles.infoLabel}>Especie:</span> {props.specie}
                    </p>
                    <p className={styles.infoText}>
                        <span className={styles.infoLabel}>Raza:</span> {props.breed}
                    </p>
                </div>
            </div>
        </div>
        <ModalComponent open={openModal} onClose={handleCloseModal} >
            <PetInfoComponent setData={props.setData} color={props.color} dob={props.dob} gender={props.gender} name={props.name} specie={props.specie} breed={props.breed} id={props.id} setCloseModal={handleCloseModal} />
        </ModalComponent>
        </>
    );
}

export default PetCardComponent