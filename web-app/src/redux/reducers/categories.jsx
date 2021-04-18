import {GET_CATEGORIES} from '../../actions/type'

const initialState = {
    categories: [],
    loaded: false,
};

export default function(state = initialState, action) {
    switch(action.type) {
        case GET_CATEGORIES:
            return {
                //take the state and change its property 'products' value:
                ...state,
                loaded: true,
                categories: action.payload //all products, we got with axios.get method in 'actions'
            }
        default:
            return state
    }
}