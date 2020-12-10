package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ActiveSubscriptionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.SubscriptionDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author bona
 */
@FeignClient(name = "abonnement", url = "#{'${abonnement.mcs.url}'}")
public interface AbonnementMCS {

    @GetMapping(path = "/subscription/access/{displayName}")
    public ActiveSubscriptionDTO permissionAccess(@PathVariable("displayName") String namePerm);
    
    @GetMapping(path = "/subscription/active/user")
    public ActiveSubscriptionDTO getUserActiveSubscription();
    
    @GetMapping(path = "/subscription/get")
    public SubscriptionDTO getSubscription(@RequestParam("subscriptionId") String subscriptionId);
    
    @GetMapping(path = "/subscription/access/decrement/{permissionName}")
    public ActiveSubscriptionDTO administratedAccess(@PathVariable("permissionName") String permissionName);

    @PostMapping(value = {"/subscription/toSubscribe"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActiveSubscriptionDTO subscribeUser(
            @RequestParam(value = "idSubscription", required = true) String idSubscription,
            @RequestParam(value = "idUser", required = true) String idUser,
            @RequestParam(value = "autoRenew", required = true) Boolean autoRenew);
    
    @PostMapping(value = {"/subscription/anonymous/subscribeToFreemium/{userId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void subscribeToFreemium(@PathVariable("userId") String userId);
    
    @PostMapping(value = {"/subscription/anonymous/subscribeToPremium/{userId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void subscribeTestUserToPremium(@PathVariable("userId") String userId);

}