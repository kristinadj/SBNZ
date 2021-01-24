<template>
    <div>
        <h1>Home</h1>
        <p>Your role is: <strong>{{currentUser.role}}</strong>.</p>
    </div>
</template>

<script>
import { authenticationService } from '@/_services';

export default {
    data () {
        return {
            currentUser: authenticationService.currentUserValue
        };
    },
    created () {
        
    }
};
</script>