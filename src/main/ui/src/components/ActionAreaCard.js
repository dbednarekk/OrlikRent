import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import { Button, CardActionArea, CardActions } from "@mui/material";
import styles from "../styles/Card.module.css";
export default function MultiActionAreaCard(props) {
  return (
    <Box sx={{ boxShadow: "20px 10px 5px #aaaaaa" }}>
      <Card sx={{ maxWidth: 500 }} className={styles.card}>
        <CardActionArea>
          <CardMedia
            component="img"
            height="500"
            image={props.image}
            alt="Pitch"
          />
          <CardContent>
            <Typography
              gutterBottom
              variant="h6"
              component="div"
              align="center"
            >
              {props.title}
            </Typography>
            <Typography variant="subtitle2" color="text.primary" align="center">
              {props.description}
            </Typography>
          </CardContent>
        </CardActionArea>
        <CardActions>
          <Button size="small" color="primary" style={{ margin: "auto" }}>
            Choose
          </Button>
        </CardActions>
      </Card>
    </Box>
  );
}
