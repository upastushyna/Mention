import React from 'react'
import loader from '../img/loader-feed.gif'

const Test = React.forwardRef((props, ref) => (
  <button ref={ref} className="FancyButton">
    {props.children}
  </button>
));


export default Test;
