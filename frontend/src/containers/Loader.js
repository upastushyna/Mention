import React from 'react'
import loader from '../img/loader-feed.gif'

const Loader = props => {
  return (
      <div className="loader">
        <img src={loader} alt="" className="loader__img"/>
      </div>
  )
};

export default Loader;
