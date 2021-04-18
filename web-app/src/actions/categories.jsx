import axios from "axios";
import {GET_CATEGORIES} from "./type";

export const getCategories = () => async (dispatch) => {
    try {
      const res = await axios.get('http://localhost:8765/category-service/categories');

      dispatch({
        type: GET_CATEGORIES,
        payload: res.data,
      });
    } catch (err) {
      console.log(err);
    }
};
