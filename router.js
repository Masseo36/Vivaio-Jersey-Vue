import Vue from "vue";
import Router from "vue-router";


import Employee from "./components/Employee.vue";
import AddEmployee from "./components/AddEmployee.vue";
import EmployeesList from "./components/EmployeesList.vue";
import SearchEmployee from "./components/SearchEmployee.vue";
import UpdateEmployee from "./components/UpdateEmployee.vue";
import DettagliEmployee from "./components/DettagliEmployee.vue";

import Mezzo from "./components/Mezzo.vue";
import AddMezzo from "./components/AddMezzo.vue"
import MezziList from "./components/MezziList.vue";
import SearchMezzo from "./components/SearchMezzo.vue";
import UpdateMezzo from "./components/UpdateMezzo.vue";
import DettagliMezzo from "./components/DettagliMezzo.vue";

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            name: "employees",
            alias: "/employee",
            component: EmployeesList,
        },

        /* Questo è il path originale del "dettaglio-employee", io ho cambiato path aggiungendo un component
        perchè altrimenti non riuscivo ad effettuare il refresh (mi dava una pagina senza dati)*/
        {
            path: "/employee/:id",
            name: "employee-details",
            component: Employee,
            props: true
        },
        // Questo è il component creato per vedere i dettagli di "employee"
        {
            path: "/employees/update/:id",
            name: "employee-update",
            component: UpdateEmployee,
            props: true
        },
        {
            path: "/employees/dettagli/:id",
            name: "employee-dettagli",
            component: DettagliEmployee,
            props: true
        },

        {
            path: "/create",
            name: "create",
            component: AddEmployee
        },
        {
            path: "/ricercaEmployee",
            name: "ricercaEmployee",
            component: SearchEmployee
        },
        {
            path: "/ricercaMezzi",
            name: "ricercaMezzi",
            component: SearchMezzo
        },
        {
            path: "/addMezzo",
            name: "addMezzo",
            component: AddMezzo
        },
        {
            path: "/mezzi",
            name: "mezzi",
            alias: "/mezzo",
            component: MezziList,
        },
        /* Questo è il path originale del "dettaglio-mezzo", io ho cambiato path aggiungendo un component
   perchè altrimenti non riuscivo ad effettuare il refresh (mi dava una pagina senza dati)*/
        {
            path: "/mezzo/:idMezzo",
            name: "mezzo-details",
            component: Mezzo,
            props: true
        },
         // Questo è il component creato per vedere i dettagli di "mezzo"
        {
            path: "/mezzi/dettagli/:idMezzo",
            name: "mezzo-dettagli",
            component: DettagliMezzo,
            props: true
        },
        {
            path: "/mezzi/update/:idMezzo",
            name: "mezzo-update",
            component: UpdateMezzo,
            props: true
        },
    ]
});