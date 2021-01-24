import { authenticationService } from '@/_services';

export function handleResponse(response) {
    return response.text().then(text => {
        if (!response.ok) {
            if ([401, 403].indexOf(response.status) !== -1) {
                authenticationService.logout();
                location.reload(true);
            }

            const error = text || response.statusText;
            return Promise.reject(error);
        } else {
            const data = text && JSON.parse(text);
            return data;
        }
    });
}