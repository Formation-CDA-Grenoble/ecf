import React from 'react';
import { Component } from 'react';
import { Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck, faTimes } from '@fortawesome/free-solid-svg-icons';

export default class InputField extends Component {
  state = {
    value: null,
    editing: false,
  }

  componentDidMount() {
    const { value } = this.props;
    this.setState({ value })
  }

  turnEditingOn = () => {
    this.setState({ editing: true });
  }

  turnEditingOff = () => {
    const { value } = this.props;

    this.setState({
      editing: false,
      value
    });
  }

  handleChange = (event) => {
    this.setState({ value: event.target.value });
  }

  handleSubmit = (event) => {
    const { updateBookProperty } = this.props;
    const { value } = this.state;
    event.preventDefault();
    updateBookProperty(value);
    this.setState({
      editing: false,
    })
  }

  render() {
    const { value, editing } = this.state;

    if (!editing) {
      return <div onDoubleClick={this.turnEditingOn}>{value}</div>
    }

    return ( 
      <form onSubmit={this.handleSubmit}>
        <input type="text" value={value} onChange={this.handleChange} />
        <Button variant="success" size="sm" type="submit"><FontAwesomeIcon icon={faCheck} /></Button>
        <Button variant="danger" size="sm" onClick={this.turnEditingOff}><FontAwesomeIcon icon={faTimes} /></Button>
      </form>
    )
  }
}
