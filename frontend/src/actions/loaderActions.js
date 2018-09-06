export function startLoader (type) {
  return {
      type,
      payload: true
    }
}

export function stopLoader (type) {
  return {
      type,
      payload: false
    }
}