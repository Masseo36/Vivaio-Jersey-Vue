import Vue from 'vue'
import Vuex from 'vuex'
import moduleA from './modules/employeeModule'
import moduleB from './modules/mezziModule'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)
export default new Vuex.Store({
    plugins: [createPersistedState()],
    modules: {
        moduleA,
        moduleB
    },
    state: {
        //  employees: [{ name: 'Max', surname: 'Vit' }, { name: 'Jon', surname: 'Ford' }]
        events: [{ date: "21/12/2008", band: "Jovanotti" }, { date: "12/07/2015", band: "Litfiba" }]
    },
    getters: {
        getEvents: (state) => {
            var eventsList = state.events.map(event => {
                return {
                    date: event.date,
                    band: event.band
                };
            });
            return eventsList
        },
        eventsWithStars: (state) => {
            var eventsWithStars = state.events.map(event => {
                return {
                    date: "*** " + event.date + " ***",
                    band: "*** " + event.band + " ***"
                };
            });
            return eventsWithStars
        },
    },
    mutations: {

    },
    actions: {

    },
})


