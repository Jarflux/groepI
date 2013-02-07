package be.kdg.Controller;

/**
 * Author: Ben Oeyen
 * Date: 7/02/13
 * Class:
 * Description:
 *
 * GET: with id, returns matching Fund
 * GET: without id returns all Funds
 * PUT: updates a Fund
 * POST: creates a Fund
 * DELETE: deletes a Fund
 */

//public class _ExampleController {
//    @RequestMapping(value = "/rest/funds/{fundId}", method = RequestMethod.GET)
//       public ModelAndView getFund(@PathVariable("fundId") String fundId_p) {
//                    Fund fund = null;
//
//                    /* validate fund Id parameter */
//                    if (isEmpty(fundId_p) || fundId_p.length() < 5) {
//                         String sMessage = "Error invoking getFund - Invalid fund Id parameter";
//                 return createErrorResponse(sMessage);
//            }
//
//                    try {
//                         fund = fundService_i.getFundById(fundId_p);
//            } catch (Exception e) {
//                         String sMessage = "Error invoking getFund. [%1$s]";
//                 return createErrorResponse(String.format(sMessage, e.toString()));
//            }
//
//                    logger_c.debug("Returing Fund: " + fund.toString());
//            return new ModelAndView(jsonView_i, DATA_FIELD, fund);
//       }
//
//     @RequestMapping(value = "/rest/funds/", method = RequestMethod.GET)
//       public ModelAndView getFunds() {
//                   List<Fund> funds = null;
//
//                    try {
//                         funds = fundService_i.getAllFunds();
//            } catch (Exception e) {
//                         String sMessage = "Error getting all funds. [%1$s]";
//                 return createErrorResponse(String.format(sMessage, e.toString()));
//            }
//
//                    logger_c.debug("Returing Funds: " + funds.toString());
//            return new ModelAndView(jsonView_i, DATA_FIELD, funds);
//       }
//
//       @RequestMapping(value = { "/rest/funds/" }, method = { RequestMethod.POST })
//       public ModelAndView createFund(@RequestBody Fund fund_p,
//                                              HttpServletResponse httpResponse_p, WebRequest request_p) {
//
//                    Fund createdFund;
//            logger_c.debug("Creating Fund: " + fund_p.toString());
//
//                    try {
//                         createdFund = fundService_i.createFund(fund_p);
//            } catch (Exception e) {
//                        String sMessage = "Error creating new fund. [%1$s]";
//                 return createErrorResponse(String.format(sMessage, e.toString()));
//           }
//
//                    /* set HTTP response code */
//                    httpResponse_p.setStatus(HttpStatus.CREATED.value());
//
//                   /* set location of created resource */
//                    httpResponse_p.setHeader("Location", request_p.getContextPath() + "/rest/funds/" + fund_p.getFundId());
//
//              // Return the view
//                   return new ModelAndView(jsonView_i, DATA_FIELD, createdFund);
//       }
//
//       @RequestMapping(value = { "/rest/funds/{fundId}" }, method = { RequestMethod.PUT })
//       public ModelAndView updateFund(@RequestBody Fund fund_p, @PathVariable("fundId") String fundId_p,
//                                                   HttpServletResponse httpResponse_p) {
//
//                    logger_c.debug("Updating Fund: " + fund_p.toString());
//
//                   /* validate fund Id parameter */
//                    if (isEmpty(fundId_p) || fundId_p.length() < 5) {
//                         String sMessage = "Error updating fund - Invalid fund Id parameter";
//                return createErrorResponse(sMessage);
//           }
//
//                    Fund fund = null;
//
//                    try {
//                         fund = fundService_i.updateFund(fund_p);
//            } catch (Exception e) {
//                        String sMessage = "Error updating fund. [%1$s]";
//                 return createErrorResponse(String.format(sMessage, e.toString()));
//            }
//
//                    httpResponse_p.setStatus(HttpStatus.OK.value());
//            return new ModelAndView(jsonView_i, DATA_FIELD, fund);
//       }
//
//       @RequestMapping(value = "/rest/funds/{fundId}", method = RequestMethod.DELETE)
//       public ModelAndView removeFund(@PathVariable("fundId") String fundId_p,
//                                                    HttpServletResponse httpResponse_p) {
//
//                    logger_c.debug("Deleting Fund Id: " + fundId_p.toString());
//
//                    /* validate fund Id parameter */
//                    if (isEmpty(fundId_p) || fundId_p.length() < 5) {
//                         String sMessage = "Error deleting fund - Invalid fund Id parameter";
//                 return createErrorResponse(sMessage);
//            }
//
//                    try {
//                         fundService_i.deleteFund(fundId_p);
//            } catch (Exception e) {
//                         String sMessage = "Error invoking getFunds. [%1$s]";
//                 return createErrorResponse(String.format(sMessage, e.toString()));
//            }
//
//                    httpResponse_p.setStatus(HttpStatus.OK.value());
//            return new ModelAndView(jsonView_i, DATA_FIELD, null);
//       }
//}
