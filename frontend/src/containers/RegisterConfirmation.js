import React from 'react';

const RegisterConfirmation = props => {
  fetch('/api/register/' + props.userToken,
    {
      method: 'POST'
    });
  return "";
};

export default RegisterConfirmation