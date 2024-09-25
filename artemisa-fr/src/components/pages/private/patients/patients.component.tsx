import { useState, useEffect } from "react";
import TableComponent from "@/components/UX/molecules/tables/table.component";
import { useNavigate } from "react-router-dom";
import useFetch from "@/hooks/fetch.hook";
import {
  IPatients,
  PatientsService,
} from "@/services/patients/patients.service";
import SubmitButton from "@/components/UX/atoms/buttons/submitButtonLoginRegister.component";
import {
  Container,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  TextField,
  Button,
  Typography,
  Box,
} from "@mui/material";
import { useForm, useWatch } from "react-hook-form";
import ModalComponent from "@/components/UX/atoms/modals/modal.components";
import PetInfoComponent from "@/components/UX/molecules/modals/Patients/petInfo.component";

const patientColumns = [
  { key: "name", label: "Nombre" },
  { key: "specie", label: "Especie" },
  { key: "breed", label: "Raza" },
  { key: "gender", label: "Género" },
  { key: "dob", label: "Fecha de Nacimiento" },
  { key: "weight", label: "Peso" },
  { key: "alimentation", label: "Alimentación" },
  { key: "color", label: "Color" },
  { key: "sterilized", label: "Esterilizado" },
  { key: "tutorName", label: "Nombre del tutor" },
  { key: "singlePatient", label: "Ver Paciente" },
];

export default function PatientsComponent() {
  const { control, setValue, getValues, reset } = useForm({
    defaultValues: {
      species: "",
      tutorIdentity: "",
    },
  });

  const [openModal, setOpenModal] = useState(false);
  const [selectedPatient, setSelectedPatient] = useState<IPatients | null>(
    null
  );

  const handleOpenModal = () => setOpenModal(true);
  const handleCloseModal = () => setOpenModal(false);

  const [page, setPage] = useState(0); // Página actual
  const [rowsPerPage, setRowsPerPage] = useState(10); // Filas por página

  const navigate = useNavigate();
  const [query, setQuery] = useState<string>("");

  const species = useWatch({ control, name: "species" });
  const tutorIdentity = useWatch({ control, name: "tutorIdentity" });

  const handleViewPatient = (patient: IPatients) => {
    setSelectedPatient(patient);
    // navigate(`/patient/${id}`);
    handleOpenModal();
  };

  const buildQuery = () => {
    const values = getValues();
    const query = [];

    if (values.species) {
      query.push(`specie=${values.species}`);
    }

    if (values.tutorIdentity) {
      query.push(`tutorIdentity=${values.tutorIdentity}`);
    }

    query.push(`limit=${rowsPerPage}`);
    query.push(`page=${page + 1}`);

    return query.join("&");
  };

  const { data: patients = [] }: { data: IPatients[] } = useFetch(async () => {
    const response = await PatientsService.getAll(query);
    console.log("response desde hook", response);
    return response;
  }, [query]);

  const handleFetchPatients = () => {
    const queryString = buildQuery();
    setQuery(queryString);
  };

  const handleChangePage = (_: unknown, newPage: number) => {
    setPage(newPage);
    handleFetchPatients();
  };

  const handleChangeRowsPerPage = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
    handleFetchPatients();
  };

  const handleReset = () => {
    reset(); // Restablece los valores del formulario a sus valores predeterminados
    setQuery(""); // Limpia las queries
    setPage(0); // Restablece la página a 0
    setRowsPerPage(10); // Restablece las filas por página a 10
  };

  const rows = patients.map((patient) => {
    const date = new Date(patient.dob);
    const formattedDate = date.toISOString().split("T")[0];
    const color = patient.color ? patient.color : "No especificado";
    const sterilized = patient.sterilized
      ? "Sí"
      : patient.sterilized === false
      ? "No"
      : "No especificado";
    return {
      name: patient.name,
      specie: patient.specie,
      breed: patient.breed,
      gender: patient.gender,
      dob: formattedDate,
      weight: patient.weight,
      alimentation: patient.alimentation,
      color: color,
      sterilized: sterilized,
      tutorName: patient.tutor.name,
      singlePatient: (
        <SubmitButton
          text="Ver Paciente"
          variant="contained"
          color="primary"
          onClick={() => handleViewPatient(patient)}
        />
      ),
    };
  });

  const speciesOptions = [
    { value: "", label: "Todas las especies" },
    { value: "Felino", label: "Felino" },
    { value: "Canino", label: "Canino" },
    { value: "Ave", label: "Ave" },
    { value: "Roedor", label: "Roedor" },
    { value: "Conejo", label: "Conejo" },
    { value: "Otro", label: "Otro" },
  ];

  return (
    <>
      <Container
        maxWidth={false}
        sx={{
          width: "100vw",
          minHeight: "100vh",
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "center",
          padding: "3%",
        }}
      >
        <Typography
          variant="h1"
          component="h1"
          gutterBottom
          sx={{ color: "primary.main" }}
        >
          Pacientes
        </Typography>
        <Box
          gap={2}
          mb={2}
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            width: "90%",
          }}
        >
          <FormControl
            variant="outlined"
            fullWidth
            sx={{
              width: { xs: "100%", md: "30%" },
              borderRadius: "10px",
              backgroundColor: "primary.light",
              "& fieldset": {
                borderColor: "complementary.main",
                borderRadius: "10px",
              },
              "&:hover fieldset": {
                borderColor: "complementary.main",
              },
            }}
          >
            <InputLabel id="species-select-label">
              Filtrar por especie
            </InputLabel>
            <Select
              labelId="species-select-label"
              id="species-select"
              value={species || ""}
              onChange={(e) => setValue("species", e.target.value)}
              label="Filtrar por especie"
            >
              {speciesOptions.map((option) => (
                <MenuItem key={option.value} value={option.value}>
                  {option.label}
                </MenuItem>
              ))}
            </Select>
          </FormControl>

          <TextField
            label="Número de Identificación del Tutor"
            variant="outlined"
            fullWidth
            value={tutorIdentity || ""}
            onChange={(e) => setValue("tutorIdentity", e.target.value)}
            sx={{
              width: { xs: "100%", md: "30%" },
              borderRadius: "10px",
              backgroundColor: "primary.light",
              "& fieldset": {
                borderColor: "complementary.main",
                borderRadius: "10px",
              },
              "&:hover fieldset": {
                borderColor: "complementary.main",
              },
            }}
          />

          <SubmitButton
            text="Filtrar Pacientes"
            variant="contained"
            color="primary"
            onClick={handleFetchPatients}
          />

          <SubmitButton
            text="Borar filtros"
            variant="contained"
            color="complementary"
            colorProperties="dark"
            onClick={handleReset}
          />
        </Box>
        <TableComponent
          columns={patientColumns}
          rows={rows}
          page={page}
          rowsPerPage={rowsPerPage}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </Container>
      <ModalComponent open={openModal} onClose={handleCloseModal}>
        {selectedPatient ? (
          <PetInfoComponent
            id={selectedPatient.id}
            name={selectedPatient.name}
            specie={selectedPatient.specie}
            gender={selectedPatient.gender}
            setCloseModal={handleCloseModal}
            tutorId={selectedPatient.tutor.id}
          />
        ) : (
          <div /> // Un elemento vacío para asegurar que siempre se pase un ReactElement
        )}
      </ModalComponent>
    </>
  );
}
