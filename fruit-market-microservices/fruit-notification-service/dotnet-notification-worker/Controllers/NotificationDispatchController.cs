using Microsoft.AspNetCore.Mvc;

namespace FruitNotification.Api.Controllers;

[ApiController]
[Route("api/v1/notifications")]
public class NotificationDispatchController : ControllerBase
{
    [HttpPost("payment-receipt")]
    public IActionResult SendPaymentReceipt([FromBody] object payload)
    {
        return Ok(new { delivered = true, engine = "ASPNET_CORE_8" });
    }

    [HttpPost("cold-chain-alert")]
    public IActionResult SendColdChainAlert([FromBody] object payload)
    {
        return Ok(new { delivered = true, engine = "ASPNET_CORE_8" });
    }
}
