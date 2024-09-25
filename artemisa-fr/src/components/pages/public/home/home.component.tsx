import { Box, Button, Typography } from "@mui/material";
import Card from "./components/card.component";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react"
import { IService, ServicesService } from "@/services/services/services.service"


const Home = () => {
  const navigate = useNavigate();
  const [services, setServices] = useState<IService[]>([])
  useEffect(() => {
    const fetchServices = async () => {
      const services = await ServicesService.getAll()
      setServices(services)
    }
    fetchServices()
  }, [])
  return (
    <Box
      sx={{
        height: "90vh",
        width: "100vw",
        display: "flex",
        flexDirection: "column",
        margin: "0",
      }}
    >
      <Box
        sx={{
          backgroundColor: "dark.main",
          width: "100%",
          height: "60%",
          display: "flex",
          gap: "10px",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            width: "30%",
            height: "90%",
          }}
        >
          <Box
            component="img"
            src="static/assets/lindo-perro-sacando-lengua-ilustracion-icono-dibujos-animados.png"
            sx={{ height: "100%", objectFit: "contain" }}
            ></Box>
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-around",
            alignItems: "center",
            width: "40%",
            height: "90%",
          }}
        >
          <Box
            sx={{
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Typography variant="h2" sx={{ color: "ligth.main" }}>
              Artemisa
            </Typography>
            <Typography variant="h3" sx={{ color: "ligth.main" }}>
              Vet solutions
            </Typography>
          </Box>
          <Typography variant="body1" sx={{ color: "ligth.main" }}>
            Lorem ipsum dolor sit amet consectetur, adipisicing elit. Placeat
            veritatis, quidem unde in dicta tempora asperiores animi nihil
            molestias velit{" "}
          </Typography>
          <Button
            variant="contained"
            sx={{
              backgroundColor: "complementary.main",
              "&:hover": {
                backgroundColor: "#ed411A",
              },
            }}
            onClick={()=>{navigate('/appointments')}}
          >
            Ir a nuestro managment
          </Button>
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            width: "25%",
            height: "50%",
            justifyContent: "space-around",
          }}
        >
          {/* <Typography variant="h2" sx={{ color: "secondary.main" }}>
            Nosotros
          </Typography>
          <Box sx={{ backgroundColor: '#FFFFFF20', padding:'10px', borderRadius:'10px'}} >
            <Typography variant="body1" sx={{ color: "ligth.main" }}>
              Lorem ipsum dolor sit amet consectetur, adipisicing elit. Placeat
              veritatis, quidem unde in dicta tempora asperiores animi nihil
              molestias velit
            </Typography>
          </Box> */}
        </Box>
      </Box>
      <Box
        sx={{
          backgroundColor: "ligth.main",
          width: "100%",
          height: "40%",
          padding: "40px",
          display: "flex",
          justifyContent: "space-around",
        }}
      >
        {services.map((service, index) => (
          <Card
            key={index}
            name={service.name}
          ></Card>
        ))
          }
      </Box>
    </Box>
  );
};

export default Home;
