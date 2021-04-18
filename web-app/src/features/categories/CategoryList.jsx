import React, { useEffect } from 'react';
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { getCategories } from "../../actions/categories";

export function CategoryList() {

    const dispatch = useDispatch();

    const loaded = useSelector(state => state.categories.loaded)
    const categories = useSelector(state => state.categories.categories)

    useEffect(() => {
      if (!loaded) {
        dispatch(getCategories())
      }
    }, [categories, dispatch])

    const handleDelete = (id) => {
        console.log("TODO:// Delete category")
    };

    return (
      <div className="container">
        <div className="row">
          <h1>List of categories</h1>
        </div>
        <div className="row">
            <Link to={ `/add-category` }>
              <button className="btn btn-primary">Add category</button>
            </Link>
        </div>
        <div className="row">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Code</th>
                <th>Description</th>
                <th>Action</th>
              </tr>
            </thead>
            {loaded &&
              <tbody>
                  {categories.map(({ id, name, code, description }, i) => (
                      <tr key={i}>
                          <td>{i}</td>
                          <td>{name}</td>
                          <td>{code}</td>
                          <td>{description}</td>
                          <td>
                              <button
                                className="btn btn-danger"
                                onClick={() => handleDelete(id)}
                              >
                                Delete
                              </button>

                              <Link to={`/edit-category/${id}`}>
                                  <button className="btn btn-success">Edit</button>
                              </Link>
                          </td>
                      </tr>
                  ))}
              </tbody>
            }
          </table>
        </div>
      </div>
    );
  }