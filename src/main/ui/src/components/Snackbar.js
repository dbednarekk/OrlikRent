
import {useSnackbar} from "notistack";
export function useSnackbarQueue(props) {
    const {enqueueSnackbar} = useSnackbar();

    return (message) => {
        enqueueSnackbar(message, {
            variant: props,
            anchorOrigin: {
                vertical: 'bottom',
                horizontal: 'left',
            }
          
        })
    }
}

export default useSnackbarQueue;
