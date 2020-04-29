const moduleB = {
    namespaced: true,
    state: {
        mezzi: []
    },
    getters: {

    },
    mutations: {
        get_mezzi(state, mezzi) {
            state.mezzi = mezzi;

        },
    },
    actions: {
        popolaStore({ commit }, mezzi) {
            commit('get_mezzi', mezzi)
        },
        deleteMezzo({ commit }, payload) {
            commit('delete_mezzo', payload)
        }
    },
}

export default moduleB