import Vue from "vue";
import Router from "vue-router";

import { authenticationService } from "@/_services";
import { Role } from "@/_helpers";
import HomePage from "@/components/home/HomePage";
import VehiclePage from "@/components/expert/VehiclePage";
import AddFailurePage from "@/components/expert/AddFailurePage";
import AddRelatedFailuresPage from "@/components/expert/AddRelatedFailuresPage";
import ReportFailurePage from "@/components/user/ReportFailurePage";
import LoginPage from "@/components/login/LoginPage";
import RegisterPage from "@/components/register/RegisterPage";
import FailureHistoryPage from "@/components/user/FailureHistoryPage";
import FailuresPage from "@/components/failure/FailuresPage";

Vue.use(Router);

export const router = new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      component: HomePage,
      meta: { authorize: [] },
    },
    {
      path: "/vehicles",
      component: VehiclePage,
      meta: { authorize: [Role.Admin] },
    },

    {
      path: "/failures",
      component: FailuresPage,
      meta: { authorize: [Role.Admin] },
    },
    {
      path: "/add-failure",
      component: AddFailurePage,
      meta: { authorize: [Role.Admin] },
    },
    {
      path: "/related-failures",
      component: AddRelatedFailuresPage,
      meta: { authorize: [Role.Admin] },
    },
    {
      path: "/report-failure",
      component: ReportFailurePage,
      meta: { authorize: [Role.User] },
    },
    {
      path: "/failure-history",
      component: FailureHistoryPage,
      meta: { authorize: [Role.User] },
    },
    {
      path: "/login",
      component: LoginPage,
    },
    {
      path: "/register",
      component: RegisterPage,
    },

    // otherwise redirect to home
    { path: "*", redirect: "/" },
  ],
});

router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  const { authorize } = to.meta;
  const currentUser = authenticationService.currentUserValue;

  if (authorize) {
    if (!currentUser) {
      // not logged in so redirect to login page with the return url
      return next({ path: "/login", query: { returnUrl: to.path } });
    }

    // check if route is restricted by role
    if (authorize.length && !authorize.includes(currentUser.role)) {
      // role not authorised so redirect to home page
      return next({ path: "/" });
    }
  }

  next();
});
