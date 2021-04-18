import { configureStore } from "@reduxjs/toolkit";
import categoriesReducer from "./redux/reducers/categories";

export default configureStore({
    reducer: {
        categories: categoriesReducer
    },
});
