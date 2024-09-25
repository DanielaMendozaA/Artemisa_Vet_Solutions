import { CalendarFold, Clock2, PawPrint } from 'lucide-react';
import styles from '../styles.module.css'
import { Typography } from '@mui/material';



interface AppointmentCardProps {

    service?: string;
    date: string;
    time: string;
    pet: string;
    state: string;

}

function AppointmentCardComponent({ service, date, time, pet, state }: AppointmentCardProps) {

    return (
        <div className={styles.appointmentCard}>
            <div className={styles.content}>
                <div className={styles.appointmentCardHeader}>
                    <h2 className={styles.name}>{service}</h2>
                    <Typography>{state}</Typography>
                </div>
                <div className={styles.infoContainer}>
                    <div className={styles.infoContainer}>
                    <Clock2 />
                    <Typography variant='body1' className={styles.appointmentInfo}>
                        {time}
                    </Typography>
                    </div>

                    <div className={styles.infoContainer}>
                    <CalendarFold strokeWidth={2} />
                    <Typography variant='body1' className={styles.appointmentInfo}>
                        {date}
                    </Typography>
                    </div>

                    <div className={styles.infoContainer}>
                    <PawPrint strokeWidth={2} />
                    <Typography variant='body1' className={styles.appointmentInfo}>
                        {pet}
                    </Typography>
                    </div>
                
                </div>
            </div>
        </div>
    );
}

export default AppointmentCardComponent;