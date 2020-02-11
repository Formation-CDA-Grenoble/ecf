import React, { Component } from 'react';
import Axios from 'axios';
import Books from './Books';

export default class DataContainer extends Component {
  state = {
    data: null,
  }

  async componentDidMount() {
    const response = await Axios.get('http://localhost:8080/api/books');
    this.setState({ data: response.data });
  }

  deleteBook = (id) => () => {
    if (window.confirm("Attention, vous allez supprimer un truc!!!")) {
      Axios.delete(`http://localhost:8080/api/books/${id}`)
      .then( response => {
          const { data } = this.state;

          this.setState({ data: data.filter( item => item.id != id ) })
        }
      )
      .catch( error =>
        console.error(error)
      );
    }
  }

  updateBookProperty = (id, propName) => (newValue) => {
    Axios.patch(
      `http://localhost:8080/api/books/${id}`,
      { [propName]: newValue }
    )
    .then( response => {
      const { data } = this.state;

      this.setState({ data: data.map(
        item => {
          if (item.id === id) {
            return response.data;
          } else {
            return item;
          }
        })
      });
    })
    .catch( error =>
      console.error(error)
    )
  }

  render() {
    const { data } = this.state;

    if (data === null) {
      return 'Chargement...';
    }

    return <Books
      books={data}
      deleteBook={this.deleteBook}
      updateBookProperty={this.updateBookProperty}
    />;
  }
}
