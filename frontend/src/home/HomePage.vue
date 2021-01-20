<template>
    <div>
        <h1>Hi {{account.user.username}}!</h1>
        <p>You're logged</p>
        <p>
            <router-link to="/login">Logout</router-link>
        </p>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
    computed: {
        ...mapState({
            account: state => state.account
        })
    },
    created () {
        
    },
    methods: {
    }
};
</script>