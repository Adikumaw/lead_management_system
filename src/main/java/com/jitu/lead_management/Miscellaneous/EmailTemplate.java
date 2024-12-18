package com.jitu.lead_management.Miscellaneous;

import lombok.Data;

@Data
public class EmailTemplate {

        // Input fields (
        // user Name,
        // application Name,
        // verificationLink,
        // verificationLink,
        // EXPIRATION,
        // application Name,
        // application Name
        // )
        public static final String EMAIL_VERIFICATION_TEMPLATE = """
                        Hi %s,
                        Thanks for signing up for %s!
                        To complete your registration and access all the features, please verify your email address.

                        Click the below link to complete your registration. If you can't click the button, copy and paste the following link into your web browser:

                        %s

                        This link will expire in %d hours.\s

                        Why Verify Your Email?
                        Verifying your email address helps us keep your account secure and ensures you receive important updates from %s.

                        Having Trouble?
                        If you're having trouble verifying your email address, please contact us at this email.

                        Thanks,
                        The %s Team\
                        """;

        // input feilds (
        // user.getName(),
        // verificationLink,
        // EXPIRATION,
        // applicationName
        // )
        public static final String PASSWORD_UPDATE_VERIFICATION_TEMPLATE = """
                        Hi %s,

                        We received a request to change your account password. To proceed, click the link below:

                        %s

                        This link will expire in %d hour.

                        If you didn’t request this change, please ignore this email.

                        Thanks,
                        %s Team
                        """;

        // Format input feilds (
        // order id,
        // user name,
        // company name,
        // order date,
        // total amount,
        // order status,
        // shipping address,
        // product details list as pre formated string,
        // order trackiing link,
        // company name
        // )
        public static final String EMAIL_ORDER_TEMPLATE = """
                        Subject: Order Confirmation - Order #%d,

                        Dear %s,

                        Thank you for your order! This email confirms your purchase with %s.
                        Order Summary

                            Order Date: %s
                            Total Amount: %.2f
                            Order Status: %s
                            Shipping Address: %s

                        Order Details
                        %s

                        You can track your order status at %s.

                        If you have any questions or require assistance, please contact our customer support.

                        Thank you for shopping with us!

                        Sincerely,
                        %s
                        """;
}
