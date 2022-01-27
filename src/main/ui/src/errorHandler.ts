import useSnackbarQueue from './components/Snackbar.ts';

function useErrorHandler() {
    const showError = useSnackbarQueue('error')
    const showWarning = useSnackbarQueue('warning')

    return (error: string | any, status: number = 0) => {

        if (error === "" && status === 404) {
            showWarning("error.unauthorized")
           
            return
        }

        if (error === undefined) {
            showError('unknown error')
            return
        }

        if (!error) {
            showError('unknown error')
            return
        }
        if( status === 401 && error === "Incorrect login or password"){
            showError("Incorrect login or password")
            return
        }
        if( status === 401 && error !== "Incorrect login or password"){
            showError("Action unauthorized, please log in")
            return
        }

        if (typeof error === 'string') {
            showError(error)
            return
        }
        for (let messageArray of Object.values(error.errors)) {
            for (const message of messageArray as Array<String>) {
                showError(message as string)
            }
        }

    }
}

export default useErrorHandler;
