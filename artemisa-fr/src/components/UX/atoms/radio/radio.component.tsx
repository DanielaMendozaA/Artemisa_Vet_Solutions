import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import { ControllerRenderProps, FieldError } from 'react-hook-form';
import { FormHelperText } from '@mui/material';

interface IProps {
    items: string[];
    label: string;
    field: ControllerRenderProps<any>;
    error?: FieldError;
}

export default function RowRadio({ items, label, field, error }: IProps) {
    return (
        <FormControl component="fieldset" error={!!error}>
            <FormLabel id="radio-group-label">{label}</FormLabel>
            <RadioGroup
                row
                aria-labelledby="radio-group-label"
                name="row-radio-buttons-group"
                onChange={field.onChange}
                color='#EE6C4D'
            >
                {
                    items.map((item) => (
                        <FormControlLabel key={item} value={item} control={<Radio sx={{
                            color: '#293241', '&.Mui-checked': {
                                color: '#EE6C4D',
                            },
                        }} />} label={item} />
                    ))
                }
            </RadioGroup>
            {error && <FormHelperText>{error.message}</FormHelperText>}
        </FormControl>
    );
}
