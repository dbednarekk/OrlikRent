import useSnackbarQueue from './components/Snackbar';

function useErrorHandler() {
    const showError = useSnackbarQueue('error')
    const showWarning = useSnackbarQueue('warning')
    return ({error, status}) => {

        if ( status === 500) {
            showWarning("Internal server error")
            return
        }

        if (error === undefined) {
            showError('unknown error 1')
            return
        }

        if (!error) {
            showError('unknown error 2')
            return
        }

        if (typeof error === 'string') {
            showError(error)
            return
        }

    }
}

export default useErrorHandler;
