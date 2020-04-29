const moduleA = {
    namespaced: true,
    state: {
        //  employees: [{ name: 'Max', surname: 'Vit' }, { name: 'Jon', surname: 'Ford' }],
        employees: [],
        age: 5
    },
    getters: {
        employeesList: (state) => {
            var employeesList = state.employees.map(employee => {
                return {
                    id: employee.id,
                    name: employee.name,
                    surname: employee.surname
                };
            });
            return employeesList
        },
        getEvents: (state, getters, rootState, rootGetters) => {
            console.log(state.age)
            console.log(getters)
            console.log(rootState)
            return rootGetters.getEvents
        },
        eventsWithStars: (state, getters, rootState, rootGetters) => {
            return rootGetters.eventsWithStars
        },
    },
    mutations: {
        get_employees(state, employees) {
            state.employees = employees;

        },
        delete_employee(state, payload) {
            const index = payload.arrayDaRidurre.indexOf(payload.oggettoAttuale);
            payload.arrayDaRidurre.splice(index, 1);
            state.employees = payload.arrayDaRidurre;
        },
        increase_add(state) {
            state.age = 4
            console.log(state.age)
        }
    },
    actions: {
        popolaStore({ commit }, employees) {
            commit('get_employees', employees)
        },
        deleteEmployee({ commit }, payload) {
            commit('delete_employee', payload)
        },
        increase({ state, commit }) {
            if (state.age == 0) {
                commit('increase_add')
            }
        }
    },
}

export default moduleA