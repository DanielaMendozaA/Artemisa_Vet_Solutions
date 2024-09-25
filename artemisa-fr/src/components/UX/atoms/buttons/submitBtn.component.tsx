
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';

interface IProps {
    text: string;
    onClick?: () => any
}

export default function SubmitBtnComponent ({ text, onClick }: IProps) {
    return (
        <Stack direction="row" spacing={2} sx={{width: '90%', marginBottom:'18px'}}>
            <Button onClick={onClick} type='submit' variant="contained" sx={{backgroundColor: 'complementary.main', color: '#E0FBFC', width: '100%', fontWeight: '700', borderRadius:'10px'}}>{text}</Button>
        </Stack>
    );
}