import React from 'react'
import searchIcon from '../img/search-icon.svg'

const EmptyState = props => {
  return (
    <div className="empty-state">
      <img className="empty-state__icon" src={props.image} alt="emptystate"/>
      <h2 className="empty-state__title">{props.title}</h2>
      <p className="empty-state__msg">{props.message}</p>
    </div>
  )
};

export default EmptyState;
