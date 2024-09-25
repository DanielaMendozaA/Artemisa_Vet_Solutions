
import { Paper, Typography } from "@mui/material"
import { Biohazard, Stethoscope, Syringe, BriefcaseMedical, MonitorCheck, ShowerHead } from "lucide-react"


interface IProps {
  name: string
  // subtitle: string
  // description: string
}

const Card = ({ name }: IProps) => {
  const lowName = name.toLowerCase()
  const renderIcon = () => {
    switch (lowName) {
      case "consulta general":
        return <Stethoscope size={40}/>;
      case "radiografia":
        return <Biohazard size={40}/>;
      case "toma de muestras":
        return <Syringe size={40}/>;
      case "consulta especialista":
          return <BriefcaseMedical size={40}/>;
      case "ecografia":
          return <MonitorCheck size={40}/>;
      case "baño":
        return <ShowerHead size={40} />
        
      default:
        return null;
    }
  };
  return (
    <Paper sx={{
      width: '120px',
      height: '120px',
      borderRadius: "50%",
      display: "flex",
      flexDirection: "column",
      justifyContent: "center",
      alignItems: "center",
      transition: "all 0.3s ease",  // Suaviza la transición al hacer hover
      '&:hover': {
        boxShadow: 8,  // Aumenta la sombra
        transform: "scale(1.1)",  // Agranda ligeramente el Paper
        cursor: "pointer",  // Cambia el cursor al hacer hover
        backgroundColor: "secondary.main",  
      }

    }} 
    elevation={5}
    onClick={()=> console.log(`click on service ${lowName}`)}
    >
      {renderIcon()      }
      <Typography variant="h6" sx={{ textAlign: "center", fontSize:"16px" }}>{name}</Typography>
    </Paper>
  )
}

export default Card
